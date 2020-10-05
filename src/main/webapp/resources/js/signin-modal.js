$('.signin-form').submit(e => {
    e.preventDefault();

    const incorrectCredentialsMessage = 'Incorrect username or password.';
    const incorrectUsernameMessage = 'Incorrect username.';
    const incorrectPasswordMessage = 'Incorrect password.';

    const emailInput = e.target[0];
    const passwordInput = e.target[1];

    const isEmailValid = emailInput.value.length !== 0;
    const isPasswordValid = passwordInput.value.length !== 0;

    debugger
    if (isEmailValid && isPasswordValid) {
        let credentials = {
            login: emailInput.value,
            password : passwordInput.value
        };

        fetch('/api/marketplace/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
        }).then((response) => {
            if (response.ok) {
                hideModal('.signin-modal-wrapper');
                headerInit();
            } else {
                emailInput.setCustomValidity(incorrectCredentialsMessage);
                e.target.reportValidity();
            }

            emailInput.value = '';
            passwordInput.value = '';
        });

    } else {
        switch (true) {
            case !isEmailValid : emailInput.setCustomValidity(incorrectUsernameMessage); break;
            case !isPasswordValid : passwordInput.setCustomValidity(incorrectPasswordMessage); break;
        }

        e.target.reportValidity();
    }
})

function resetLoginValidity() {
    $('#signin-login')[0].setCustomValidity('');
    $('#signin-password')[0].setCustomValidity('');
}