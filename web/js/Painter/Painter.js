class Painter{

    static init(){
        Painter.Block.DialogList.div = document.getElementById("DialogList");
        Painter.Block.DialogList.maindiv = document.getElementById("Dialogs");
        Painter.Block.Dialog.div = document.getElementById("Dialog");
        Painter.Block.Dialog.maindiv = document.getElementById("Messages");
        document.getElementsByTagName("body")[0].style.display = "block";
    }

    static AddInDialogList(dialogInfList){
        console.log("AddInDialogList");
        console.log(dialogInfList);
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