let wkId = 0;

function arrangeDisplay() {

    if(wkId===0) {
        return ;
    }

    const bl_data_items = document.querySelectorAll(".bl_data_item");
    [...bl_data_items].forEach((item) => {
        if (item.dataset.wkId === wkId) {
            item.classList.add("hp_selected");
            item.querySelector("#wkEdit").classList.remove("hp_unVisible");
            item.querySelector("#wkRemove").classList.remove("hp_unVisible");
            item.querySelector("#wkRegisterChild").classList.remove("hp_unVisible");
            item.querySelector("#wkParent").classList.add("hp_unVisible");
        } else {
            item.classList.remove("hp_selected");
            if(item.querySelector("#wkEdit")){
                item.querySelector("#wkEdit").classList.add("hp_unVisible");
                item.querySelector("#wkRemove").classList.add("hp_unVisible");
                item.querySelector("#wkRegisterChild").classList.add("hp_unVisible");
                item.querySelector("#wkParent").classList.remove("hp_unVisible");
            }
        }
    })
    if (wkId!==0) {
        remove_wk_rankUpDown_btn();
    }
}

function selectWkId(e) {
    if (e.target.id === "wkParent"){
        return;
    }
    const bl_data_item = e.target.closest('.bl_data_item')
    if (bl_data_item) {
        wkId = bl_data_item.dataset.wkId;
        arrangeDisplay();
        console.log("now selected wkId = " + wkId);
    } else {
        return;
    }
}

function remove_wk_rankUpDown_btn() {
    const btn_rankUp = document.querySelector("#wkRankUp");
    const btn_rankDown = document.querySelector("#wkRankDown");

    btn_rankUp.classList.remove("hp_unVisible");
    btn_rankDown.classList.remove("hp_unVisible");
}
