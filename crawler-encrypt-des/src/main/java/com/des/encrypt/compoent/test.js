function f(st) {
    var pasArr = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '$', '%', '&', '@', '+', '!'];
    for (var i = st.length; i > 0; i--) {
        var randomNum = 0 + Math.floor(Math.random() * (68 - 0 + 1));
        st = st.slice(0, i - 1) + pasArr[randomNum] + st.slice(i - 1);
    }
    return st;
}


console.log(f('y2npPKJ2QSw='));