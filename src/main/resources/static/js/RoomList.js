import {roomTemplate} from "./Templates.js";

export function RoomList() {
    const $roomList = document.querySelector(".list-group");

    this.render = (rooms) => {
        const template = rooms.map(room => roomTemplate(room));
        $roomList.innerHTML = template.join("");
    };
}
