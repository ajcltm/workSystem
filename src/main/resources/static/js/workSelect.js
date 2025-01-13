let wkId = 0;

function selectWkId(e) {
    const bl_data_item = e.target.closest('.bl_data_item')
    if (bl_data_item) {
        wkId = bl_data_item.dataset.wkId;
    } else {
        return;
    }

    const bl_data_items = document.querySelectorAll(".bl_data_item");
    [...bl_data_items].forEach((item) => {
        if (item.dataset.wkId === wkId) {
            item.classList.toggle("hp_selected");
        } else {
            item.classList.remove("hp_selected");
        }
    })
    console.log("now selected wkId = " + wkId);
}