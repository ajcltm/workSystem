
function levelize() {
    const data_items = document.querySelectorAll(".bl_data_item");
    data_items.forEach((data_item) => {
        data_item.style.marginLeft = `${data_item.dataset.wkLevel}` + "rem";
    })
}