import {RoomList} from "./RoomList.js";

function Chat() {
    const $roomList = document.querySelector(".list-group");
    const $creatBtn = document.querySelector(".room-create-btn");
    const $roomName = document.querySelector(".input-group-text");
    let chatRooms = [];

    const setState = (newRooms) => {
        chatRooms = newRooms;
    }

    const roomList = new RoomList();

    const initEventListener = () => {
        $creatBtn.addEventListener("click", e => {
            e.preventDefault();
            axios.post("http://localhost:8080/chat/rooms", null, {
                params: {
                    roomName: $roomName.value
                }
            })
                .then(res => {
                    setState([...chatRooms, res.data]);
                    roomList.render(chatRooms);
                    $roomName.value = "";
                });
        });

        $roomList.addEventListener("click", e => {
            e.preventDefault();
            const id = e.target.dataset.id;
            const sender = prompt("대화명을 입력해 주세요.");
            if (sender !== "") {
                localStorage.setItem("wschat.sender", sender);
                localStorage.setItem("wschat.roomId", id);
            }
        });
    };

    this.init = () => {
        initEventListener();
    }
}

const chat = new Chat();
chat.init();
