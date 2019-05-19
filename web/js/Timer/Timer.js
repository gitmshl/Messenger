class Timer{

    static init(){
        console.log("Timer.init()");
        this.Timers[0] = {handl: -1, code: 0, count: 0};
        this.Timers[10] = {handl: -1, code: 10, count: 0};
        this.Timers[11] = {handl: -1, code: 11, count: 0};
        this.Timers[20] = {handl: -1, code: 20, count: 0};
        this.Timers[21] = {handl: -1, code: 21, count: 0};
        this.Timers[30] = {handl: -1, code: 30, count: 0};
        this.timers_0 = new Map(); /// мб еще изменим
    }

    static clearAllTimers(){
        console.log("Timer.clearAllTimers()");
        Timer.Timers.forEach(function(t){
            clearTimeout(t.handl);
        });
        /// после создания timers_0 нужно будет и их уничтожить !!!
    }

    static setTimer_10(){
        console.log("Timer.setTimer_10()");
        let t = Timer.Timers[10];
        if (t.handl != -1) clearTimeout(t.handl);
        t.handl = setTimeout(Timer.timeout_10, Consts.TIMER_10_TIME);
    }

    static clearTimer_10(){
        console.log("Timer.clearTimer_10()");
        clearTimeout(Timer.Timers[10].handl);
    }

    static timeout_10(){
        console.log("Timer.timeout_10()");
        if (SST.checkErr() || !SST.checkWaitingResponse(110)) return;
        let t = Timer.Timers[10];
        t.count = t.count + 1;
        if (t.count > Consts.MAX_TIMER_10_COUNT){
            UC.err_timer_10();
            return;
        }
        UC.req_10();
    }

    static setTimer_11(){
        console.log("Timer.setTimer_11()");
        let t = Timer.Timers[11];
        if (t.handl != -1) clearTimeout(t.handl);
        t.handl = setTimeout(Timer.timeout_11, Consts.TIMER_11_TIME);
    }

    static clearTimer_11(){
        console.log("Timer.clearTimer_11()");
        clearTimeout(Timer.Timers[11].handl);
    }

    static timeout_10(){
        console.log("Timer.timeout_11()");
        if (SST.checkErr() || !SST.checkWaitingResponse(111)) return;
        let t = Timer.Timers[11];
        t.count = t.count + 1;
        if (t.count > Consts.MAX_TIMER_11_COUNT){
            UC.err_timer_11();
            return;
        }
        UC.req_11();
    }

    static setTimer_20(){
        console.log("Timer.setTimer_20()");
        let t = Timer.Timers[20];
        if (t.handl != -1) clearTimeout(t.handl);
        t.handl = setTimeout(Timer.timeout_20, Consts.TIMER_20_TIME);
    }

    static clearTimer_20(){
        console.log("Timer.clearTimer_20()");
        clearTimeout(Timer.Timers[20].handl);
    }

    static timeout_20(){
        console.log("Timer.timeout_20()");
        if (SST.checkErr() || !SST.checkWaitingResponse(120)) return;
        let t = Timer.Timers[20];
        t.count = t.count + 1;
        if (t.count > Consts.MAX_TIMER_20_COUNT){
            UC.err_timer_20();
            return;
        }
        UC.req_20();
    }

    static setTimer_21(){
        console.log("Timer.setTimer_21()");
        let t = Timer.Timers[21];
        if (t.handl != -1) clearTimeout(t.handl);
        t.handl = setTimeout(Timer.timeout_21, Consts.TIMER_21_TIME);
    }

    static clearTimer_21(){
        console.log("Timer.clearTimer_21()");
        clearTimeout(Timer.Timers[21].handl);
    }

    static timeout_21(){
        console.log("Timer.timeout_21()");
        if (SST.checkErr() || !SST.checkWaitingResponse(121)) return;
        let t = Timer.Timers[21];
        t.count = t.count + 1;
        if (t.count > Consts.MAX_TIMER_21_COUNT){
            UC.err_timer_21();
            return;
        }
        UC.goToDialog(SST.getCurrentDialog());
    }

    static setTimer_30(){
        console.log("Timer.setTimer_30()");
        let t = Timer.Timers[30];
        if (t.handl != -1) clearTimeout(t.handl);
        t.handl = setTimeout(this.timeout_30, Consts.TIMER_30_TIME);
    }

    static clearTimer_30(){
        console.log("Timer.clearTimer_30()");
        clearTimeout(Timer.Timers[30].handl);
    }

    static timeout_30(){
        console.log("Timer.timeout_30()");
        if (SST.checkErr() || !SST.checkWaitingResponse(130)) return;
        let t = Timer.Timers[30];
        t.count = t.count + 1;
        if (t.count > Consts.MAX_TIMER_30_COUNT){
            UC.err_timer_30();
            return;
        }
        UC.start(true);
    }

    static Timers = [];
    static timers_0;
}