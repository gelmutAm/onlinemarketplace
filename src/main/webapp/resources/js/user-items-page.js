const NAME_REGEX = /^[a-zA-Z ]{1,100}$/;
const START_PRICE_REGEX = /^[1-9]{1}[0-9 ]{0,10}$/;
const STOP_DATE_REGEX = /^(((0[1-9]|[12]\d|3[01])[\/\.-](0[13578]|1[02])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|[12]\d|30)[\/\.-](0[13456789]|1[012])[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((0[1-9]|1\d|2[0-8])[\/\.-](02)[\/\.-]((19|[2-9]\d)\d{2})\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]))|((29)[\/\.-](02)[\/\.-]((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))\s(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])))$/g;

fetch('/api/marketplace/user/item').then(function (response) {
    if (response.ok) {
        response.json().then(function (json) {
            items = json;
            const itemsContainer = '.items-container';
            if (items.length > 0) {
                const itemsContainer = '.items-container';
                $(itemsContainer).append('<div class="user-item-cards-wrapper"></div>')
                initialize();
            } else {
                $(itemsContainer).append('<p class="no-items-message">Looks like you don\'t have any items.</p>');
            }
            itemCardClickListener();
        });
    } else {
        console.log('Network request for items.json failed with response ' + response.status + ': ' + response.statusText);
    }
})

function initialize() {
    var itemCardsWrapper = document.getElementsByClassName('user-item-cards-wrapper')[0];

    var finalGroup = items;
    updateDisplay();

    function updateDisplay() {
        while (itemCardsWrapper.firstChild) {
            itemCardsWrapper.removeChild(itemCardsWrapper.firstChild);
        }

        if (finalGroup.length === 0) {
            var para = document.createElement('p');
            para.textContent = 'Nothing found!';
            itemCardsWrapper.appendChild(para);
        } else {
            for (var i = 0; i < finalGroup.length; i++) {
                showItem(finalGroup[i]);
            }
        }
    }

    function showItem(item) {
        var itemCard = document.createElement('div');
        var itemCardImageWrapper = document.createElement('div');
        var itemCardImage = document.createElement('img');
        var itemCardContentWrapper = document.createElement('div');
        var itemCardContentTitle = document.createElement('h6');
        var itemCardContentSellerDate = document.createElement('div');
        var itemCardContentTextSeller = document.createElement('span');
        var itemCardContentTextDate = document.createElement('span');
        var itemCardContentPriceBids = document.createElement('div');
        var itemCardContentPriceText = document.createElement('span');
        var itemCardContentBidsText = document.createElement('span');

        itemCard.setAttribute('class', 'item-card');
        itemCard.setAttribute('id', item.id);
        itemCardImageWrapper.setAttribute('class', 'item-card-image-wrapper');
        itemCardContentWrapper.setAttribute('class', 'item-card-content-wrapper');
        itemCardContentTitle.setAttribute('class', 'item-card-content-title');
        itemCardContentSellerDate.setAttribute('class', 'item-card-content-seller-date');
        itemCardContentTextSeller.setAttribute('class', 'item-card-content-text');
        itemCardContentTextDate.setAttribute('class', 'item-card-content-text');
        itemCardContentPriceBids.setAttribute('class', 'item-card-content-price-bids');
        itemCardContentPriceText.setAttribute('class', 'item-card-content-price-text');
        itemCardContentBidsText.setAttribute('class', 'item-card-content-text');

        itemCardImage.src = item.pictureLink;
        itemCardContentTitle.textContent = item.name;
        itemCardContentTextSeller.textContent = item.seller;
        itemCardContentTextDate.textContent = item.stopDate;
        itemCardContentPriceText.textContent = '$' + item.currentPrice;
        itemCardContentBidsText.textContent = item.bidsQty + ' bids';

        itemCard.appendChild(itemCardImageWrapper);
        itemCardImageWrapper.appendChild(itemCardImage);

        itemCard.appendChild(itemCardContentWrapper);
        itemCardContentWrapper.appendChild(itemCardContentTitle);
        itemCardContentWrapper.appendChild(itemCardContentSellerDate);
        itemCardContentSellerDate.appendChild(itemCardContentTextSeller);
        itemCardContentSellerDate.appendChild(itemCardContentTextDate);

        itemCardContentWrapper.appendChild(itemCardContentPriceBids);
        itemCardContentPriceBids.appendChild(itemCardContentPriceText);
        itemCardContentPriceBids.appendChild(itemCardContentBidsText);

        itemCardsWrapper.appendChild(itemCard);
    }
}

function itemCardClickListener() {
    $('.item-card').click(function(e) {
            let id = $(this).attr('id');
            window.location.href = '/marketplace/item/' + id;
    });
}

$('.item-form').submit(e => {
    e.preventDefault();

    const nameMessage = 'Incorrect name.';
    const startPriceMessage = 'Incorrect price.';

    const nameInput = e.target[0];
    const pictureLinkInput = e.target[1];
    const startPriceInput = e.target[2];
    const stopDateInput = e.target[3];
    const descriptionInput = e.target[4];

    const isNameValid = NAME_REGEX.test(nameInput.value);
    const isStartPriceValid = START_PRICE_REGEX.test(startPriceInput.value);

    if (isNameValid && isStartPriceValid) {
        let date = new Date(stopDateInput.value);
        let dateString = date.toISOString();
        let item = {
            name: nameInput.value,
            pictureLink: pictureLinkInput.value,
            startPrice: startPriceInput.value,
            stopDate: dateString,
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

function resetItemValidity() {
    $('#name')[0].setCustomValidity('');
    $('#start-price')[0].setCustomValidity('');
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