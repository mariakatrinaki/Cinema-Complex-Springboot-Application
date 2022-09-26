
function changeColor(btn) {

    if (btn.style.backgroundColor == 'lightgreen') {
        btn.style.backgroundColor = 'red';
        btn.style.color = 'black';
    } else {
        btn.style.backgroundColor = 'lightgreen';
        btn.style.color = 'white';
    }

    console.log(btn.id);

};