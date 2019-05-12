class Timer{

    static init(){
        console.log("Timer.init()");
        this.Timers[0] = {handl: -1, code: 0, count: 0};
        this.Timers[10] = {handl: -1, code: 10, count: 0};
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
        console.log(t);
        if (t.count > Consts.MAX_TIMER_30_COUNT){
            UC.err_timer_30();
            return;
        }
        UC.start(true);
    }

    static Timers = [];
    static timers_0;
}