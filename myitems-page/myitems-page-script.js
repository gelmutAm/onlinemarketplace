const NAME_REGEX = /^[a-zA-Z ]{1,100}$/;
const START_PRICE_REGEX = /^[1-9]{1}[0-9 ]{0,10}$/;
const STOP_DATE_REGEX = /^$/;

$('.add-item-form').submit(e => {
debugger

    e.preventDefault();

    const nameMessage = 'Name can\'t be empty!';
    const startPriceMessage = 'Start Price can\'t be empty!';
    const stopDateMessage = '';

    const nameInput = e.target[0];
    const startPriceInput = e.target[2];
    const stopDateInput = e.target[3];

    const isNameValid = NAME_REGEX.test(nameInput.value);
    const isStartPriceValid = START_PRICE_REGEX.test(startPriceInput.value);
    const isStopDateValid = STOP_DATE_REGEX.test(stopDateInput.value);

    if (isNameValid && isStartPriceValid && isStopDateValid) {
        hideModal('.item-info-modal-wrapper');
        nameInput.value = '';
        startPriceInput.value = '';
        stopDateInput.value = '';
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


$('.add-item-button').click(() => {
    showModal('.item-info-modal-wrapper');
})

$('.item-info-modal-close-button').click(() => {
    hideModal('.item-info-modal-wrapper');
    $('#description').val('');
})

$('.item-info-modal-backdrop').click(() => {
    hideModal('.item-info-modal-wrapper');
    $('#description').val('');
})