class Painter{

    static init(){
        Painter.Block.DialogList.div = document.getElementById("DialogList");
        Painter.Block.DialogList.maindiv = document.getElementById("Dialogs");
        Painter.Block.Dialog.div = document.getElementById("Dialog");
        Painter.Block.Dialog.maindiv = document.getElementById("Messages");
        document.getElementsByTagName("body")[0].style.display = "block";
    }


    /**
     * Эта функция производит установку необходимых классов в необходимых элементах DOM, которые
     * отвечают за сообщение. Суть в том, что производится определение того, является ли сообщение
     * (последнее сообщение в диалоге) прочитанным, либо же нет. В зависимости от возможных вариантов,
     * производится подстановка css класса .unread в DOM
     * @param from_user_id
     * @param last_msg_time
     * @param last_read_time
     * @param my_last_reading_time
     */
    static setCorrectClassOnUnreadMessages(dialog_id, from_user_id, last_msg_time, last_read_time, my_last_reading_time){
        let my_id = SST.getId();
        last_msg_time = new Date(last_msg_time);
        last_read_time = new Date(last_read_time);
        my_last_reading_time = new Date(my_last_reading_time);
        var dialog_block = document.getElementById(dialog_id);
        if (my_id == from_user_id) {
            if (last_msg_time < last_read_time) return;
            let sub2 = dialog_block.getElementsByClassName("sub2")[0];
            sub2.classList.add("unread");
            return;
        }
        if (my_last_reading_time > last_msg_time) return;
        dialog_block.classList.add("unread");
    }

    static getRightDateTime(){
        return "0:18";
    }

    /**
     * Вставляет в DOM дерево в список диалогов 1 диалог (при этом, с учетом
     * правильных выборов классов)
     * @param dialogInf - содержится вся информация о диалоге, который нужно
     *                      вставить
     * @constructor
     */
    static InsertInDOMOneDialogBlock(dialogInf){
        console.log("InsertINDom");
        let dialog_id = dialogInf["dialog_id"];
        let dialog_img = dialogInf["dialog_img"];
        let dialog_name = dialogInf["dialog_name"];
        let from_user_id = dialogInf["from_user_id"];
        let from_user_name = dialogInf["from_user_name"];
        let last_msg = dialogInf["last_msg"];
        let last_msg_time = dialogInf["last_msg_time"];
        let last_read_time = dialogInf["last_read_time"];
        let my_last_reading_time = dialogInf["my_last_reading_time"];

        let div = document.createElement("div");
        div.className = "dialog_block";
        div.id = dialog_id;
        let avatar_block = "<div class=\"avatar_block\"><img src=\"img/user_"+ SST.getId() +"/"+ dialog_img +"\"></div>"; /// описание блока avatar_block
        let sub1 = "<div class=\"sub1\">" + dialog_name + "</div>"; /// Имя
        let sub2 = "<div class=\"sub2\" style=\"display: block;\">"+last_msg+"</div>"; /// последнее сообщение
        let date = "<div class=\"date\">"+Painter.getRightDateTime()+"</div>"; /// дата (время) последнего сообщения
        let function_onclick = "alert("+ dialog_id +");"
        div.innerHTML = avatar_block +
            "<a href=\"google.com\" class=\"profile_name_link\" onclick=\"" + function_onclick +" return false;\">" +
            "<div class=\"profile_name\">" +
            sub1 +
            sub2 +
            "<div class=\"sub3\" style=\"display: none;\"></div>" +
            date +
            "</div></a>";

        Painter.Block.DialogList.maindiv.appendChild(div);
        //// После вставки в DOM дерево, начинаем настраивать необходимые классы
        //// производим настройку класса .unread
        Painter.setCorrectClassOnUnreadMessages(dialog_id, from_user_id, last_msg_time, last_read_time, my_last_reading_time);

    }

    static AddInDialogList(dialogInfList){
        console.log("AddInDialogList1");
        let dialogs = dialogInfList["dialogInfList"];
        dialogs.forEach(function(dialog){
            Painter.InsertInDOMOneDialogBlock(dialog);
        });
    }

    static showDialogList(){
        let b = Painter.Block.DialogList;
        b.div.style.display = "block";
        b.display = true;
    }

    static hideDialogList(){
        let b = Painter.Block.DialogList;
        b.div.style.display = "none";
        b.display = false;
    }

    static showDialog(){
        let b = Painter.Block.Dialog;
        b.div.style.display = "block";
        b.display = true;
    }

    static hideDialog(){
        let b = Painter.Block.Dialog;
        b.div.style.display = "none";
        b.display = false;
    }

    static Block = {
        DialogList: {
            display: false,
            div: null,
            maindiv: null
        },
        Dialog:{
            display: false,
            div: null,
            maindiv: null
        }
    }
}