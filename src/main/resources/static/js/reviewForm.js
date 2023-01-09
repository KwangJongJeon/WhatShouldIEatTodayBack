// 모달 설정
const body = document.querySelector('body');
const modal = document.querySelector('.modal');
const btnOpenPopup = document.querySelector('.btn-open-popup');

// btnOpenPopup.addEventListener('click', () => {
//     modal.classList.toggle('show');
//
//     if (modal.classList.contains('show')) {
//         body.style.overflow = 'hidden';
//     }
// });

modal.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.classList.toggle('show');


        if (!modal.classList.contains('show')) {
            body.style.overflow = 'auto';
        }
    }
});



// 텍스트 박스 설정
const textarea = document.querySelector(".content");
textarea.addEventListener('keyup', (e) => {
    const area = document.querySelector(".content")
    var text_length = area.value.length + '/255';
    const cnt = document.querySelector(".count");
    cnt.innerText = text_length;
})

function check_length(area) {
    var text = area.value;
    var test_length = text.length;

    // 최대 글자수
    var max_length = 255;

    if(test_length > max_length) {
        alert('입력 메시지는 ' + max_length + ' 글자를 초과 할 수 없습니다.');
        text = text.substr(0, max_length);
        area.value=text;
        area.focus();
    }
}

function popUpModal(member) {
    if(!checkLogin(member)) {
        alert("로그인 후 다시 시도해주세요!")
        return;
    }

    modal.classList.toggle('show');

    if(modal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
}

// 리뷰 버튼 클릭시 로그인 여부 확인
function checkLogin(member) {
    if(member == null) return false;
    return true;
}