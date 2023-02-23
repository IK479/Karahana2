package com.example.karahana.firebase;


import android.content.Context;

import com.example.karahana.managers.Models.PartyCard;


public class DataSourceManager {

    private static DataSourceManager instance;
    public static DataSourceManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataSourceManager(context);
        }

        return instance;
    }
    private DataSourceManager(Context context){
        this.context = context;
    }
    Context context;

    public interface UpdateListener {
        void onOnPartyUpdate(PartyCard party);
        void onOnPartiesChange();
        void onError(String error);
    }

    private UpdateListener updateListener;

    enum e_loadingStatus {none, success, error}

    public void setUpdateListener(UpdateListener listener) {
        this.updateListener = listener;
    }

    DataSourceManager() {
        initParties();
    }

    public void refreshData() {
        loadPartiesThread();
    }

    // region Parties
    //
    private Parties parties;
    private Thread partiesLoadingThread;
    private e_loadingStatus loadingPartiesStatus = e_loadingStatus.none;

    private void initParties() {
        parties = PreferencesManager.getInstance(context).getParties();
        loadPartiesThread();
    }

    public void registerToPartyUpdate() {
        FirebaseManager.getInstance().registerToPartyUpdate(onPartyUpdate());
    }

    private ActionListener onPartyUpdate() {
        return result -> {
            if (result.success) {
                PartyCard party = (PartyCard) result.result;
                if (party == null) {
                    return;
                }

                if (party.deleted) {
                    return;
                } else if (!parties.containsKey(party.uid) && !party.deleted) {
                    parties.put(party.uid, party);
                    if (updateListener != null) {
                        updateListener.onOnPartiesChange();
                    }
                } else {
                    copyParty(parties.get(party.uid),party);
                    if (updateListener != null) {
                        updateListener.onOnPartyUpdate(party);
                    }
                }

                PreferencesManager.getInstance(context).saveParties(parties);
            }
        };
    }

    private void copyParty(PartyCard partySource, PartyCard partyTarget) {
        partyTarget.setBgImages(partySource.getBgImage());
        partyTarget.setPassword(partySource.getPassword());
        partyTarget.setCalenderImg(partySource.getCalenderImg());
        partyTarget.setPartyName(partySource.getPartyName());
        partyTarget.setText(partySource.getText());
        partyTarget.setTime(partySource.getTime());
        partyTarget.setPrivate(partySource.getIsPrivate());

        partyTarget.deleted = partySource.deleted;
        partyTarget.uid = partySource.uid;
    }

    private void loadPartiesThread() {
        partiesLoadingThread = new Thread(() -> {
            while (loadingPartiesStatus != e_loadingStatus.success) {
                FirebaseManager.getInstance().loadParties(result -> {
                    if (result.success) {
                        if (loadingPartiesStatus == e_loadingStatus.success) {
                            return;
                        }

                        loadingPartiesStatus = e_loadingStatus.success;
                        parties = new Parties((PartyCard[]) result.result);

                        PreferencesManager.getInstance(context).saveParties(parties);
                        if (updateListener != null) {
                            ThreadUtils.runOnUI(() -> {
                                updateListener.onOnPartiesChange();
                            });
                        }
                        registerToPartyUpdate();
                        partiesLoadingThread = null;
                    } else {
                        if (loadingPartiesStatus == e_loadingStatus.success) {
                            return;
                        }

                        loadingPartiesStatus = e_loadingStatus.error;
                        if (updateListener != null) {
                            ThreadUtils.runOnUI(() -> {
                                updateListener.onError(result.error);
                            });
                        }
                    }
                });

                ThreadUtils.sleep(30000);
            }
        });
        partiesLoadingThread.setPriority(Thread.MIN_PRIORITY);
        partiesLoadingThread.start();
    }

    public PartyCard[] getParties() {
        if (parties == null) {
            return null;
        }

        return parties.asArray();
    }

    public void addParty(PartyCard party, ActionListener listener) {
        FirebaseManager.getInstance().addParty(party, result -> {
            if (result.success) {
                PartyCard newParty = (PartyCard) result.result;
                if (!parties.containsKey(newParty.uid)) {
                    parties.put(newParty.uid, newParty);
                }
            }

            listener.onResult(result);
        });
    }
    // endregion
}

