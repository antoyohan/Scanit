
package com.project.scanit.ui.rxbus;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

public class RxBusHelper {

    private DisposableObserver<Object> mDisposable;

    public void registerEvents(RxBus rxBus, final String tag, final RxBusCallback callback) {

        if (rxBus != null) {
            mDisposable = new DisposableObserver<Object>() {
                @Override
                public void onNext(Object event) {
                    callback.onEventTrigger(event);
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(tag, "Event : onError : " + e.getMessage());
                }

                @Override
                public void onComplete() {
                    Log.d(tag, "Event : onComplete");
                }
            };
            rxBus.toObservable().share().subscribeWith(mDisposable);
        }
    }

    public void unSubScribe() {

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
