const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const PASSWORD_REGEX = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;

$('.signup-form').submit(e => {
    e.preventDefault();

    const emailMessage = 'Email is not valid!';
    const passwordMessage = 'Password must be at list 8 characters including a number, uppercase and lowercase letters.';
    const confirmPasswordMessage = 'Please make sure your passwords match.';

    const emailInput = e.target[0];
    const passwordInput = e.target[1];
    const confirmPasswordInput = e.target[2];

    const isEmailValid = EMAIL_REGEX.test(emailInput.value.toLowerCase());
    const isPasswordValid = PASSWORD_REGEX.test(passwordInput.value);
    const doPasswordsMatch = (passwordInput.value === confirmPasswordInput.value);

    if (isEmailValid && isPasswordValid && doPasswordsMatch) {
        hideModal('.signup-modal-wrapper');
        emailInput.value = '';
        passwordInput.value = '';
        confirmPasswordInput.value = '';
    } else {
        switch (true) {
            case !isEmailValid : emailInput.setCustomValidity(emailMessage); break;
            case !isPasswordValid : passwordInput.setCustomValidity(passwordMessage); break;
            case !doPasswordsMatch : confirmPasswordInput.setCustomValidity(confirmPasswordMessage); break;
        }
        e.target.reportValidity(); 
    }    
})

function resetValidity() {
    $('#signup-login')[0].setCustomValidity('');
    $('#signup-password')[0].setCustomValidity('');
    $('#signup-confirm-password')[0].setCustomValidity('');
}