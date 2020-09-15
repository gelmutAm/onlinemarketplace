const NAME_REGEX = /^[a-zA-Z ]{1,100}$/;
const START_PRICE_REGEX = /^[1-9]{1}[0-9 ]{0,10}$/;
const STOP_DATE_REGEX = /^(((0[1-9]|[12]\d|3[01])[\/\.-](0[13578]|1[02])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|[12]\d|30)[\/\.-](0[13456789]|1[012])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|1\d|2[0-8])[\/\.-](02)[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((29)[\/\.-](02)[\/\.-]((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))$/g;

$('.item-form').submit(e => {

    e.preventDefault();

    const nameMessage = 'Name can\'t be empty!';
    const startPriceMessage = 'Start Price can\'t be empty!';
    const stopDateMessage = 'Stop Date format must match \'dd/MM/yyyy hh:mm:ss\'.';

    const nameInput = e.target[0];
    const pictureLinkInput = e.target[1];
    const startPriceInput = e.target[2];
    const stopDateInput = e.target[3];
    const descriptionInput = e.target[4];

    const isNameValid = NAME_REGEX.test(nameInput.value);
    const isStartPriceValid = START_PRICE_REGEX.test(startPriceInput.value);
    const isStopDateValid = STOP_DATE_REGEX.test(stopDateInput.value);

    if (isNameValid && isStartPriceValid && isStopDateValid) {
        let item = {
            name: nameInput.value,
            pictureLink: pictureLinkInput.value,
            startPrice: startPriceInput.value,
            stopDate: stopDateInput.value,
            description: descriptionInput.value,
        }
        fetch('/api/marketplace/user/item', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then((response) => {
            if (response.ok) {
                hideModal('.item-info-modal-wrapper');
                headerInit();
            } else {
                e.target.reportValidity();
            }

            nameInput.value = '';
            startPriceInput.value = '';
            stopDateInput.value = '';
        });
    } else {
        switch (true) {
            case !isNameValid : nameInput.setCustomValidity(nameMessage); break;
            case !isStartPriceValid : startPriceInput.setCustomValidity(startPriceMessage); break;
            case !isStopDateValid : stopDateInput.setCustomValidity(stopDateMessage); break;
        }
        e.target.reportValidity(); 
    }    
})

function resetValidity() {
    $('#name')[0].setCustomValidity('');
    $('#start-price')[0].setCustomValidity('');
    $('#stop-date')[0].setCustomValidity('');
}

function getDate() {
    let currentDate = new Date();
    let day = String(currentDate.getDate()).padStart(2, '0');
    let month = String(currentDate.getMonth() + 1).padStart(2, '0');
    let year = currentDate.getFullYear();
    let hours = String(currentDate.getHours()).padStart(2, '0');
    let minutes = String(currentDate.getMinutes()).padStart(2, '0');
    let seconds = String(currentDate.getSeconds()).padStart(2, '0');
    let output = day + '/' + month + '/' + year + ' ' + hours + ':' + minutes + ':' + seconds;
    return output;
}


$('.add-item-button').click(() => {
    showModal('.item-info-modal-wrapper');
    $('#stop-date').val(getDate());
})

$('.item-info-modal-close-button').click(() => {
    hideModal('.item-info-modal-wrapper');
    $('#description').val('');
})

$('.item-info-modal-backdrop').click(() => {
    hideModal('.item-info-modal-wrapper');
    $('#description').val('');
})