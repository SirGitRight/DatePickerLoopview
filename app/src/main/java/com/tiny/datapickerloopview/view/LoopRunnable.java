// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.tiny.datapickerloopview.view;

// Referenced classes of package com.qingchifan.view:
//            LoopView, LoopListener

final class LoopRunnable implements Runnable {

    final LoopView loopView;

    LoopRunnable(LoopView loopview) {
        super();
        loopView = loopview;

    }

    @Override
    public final void run() {
        LoopListener listener = loopView.loopListener;
        if (loopView.bstop )
        {
            loopView.blistener=true;
            loopView.bstop=false;
            listener.onDown(true);
        }

        int selectedItem = LoopView.getSelectedItem(loopView);
        loopView.arrayList.get(selectedItem);
        listener.onItemSelect(selectedItem);
    }
}
