// nurodom kelia kur randasi selecto dirbtinio rodyklės paveikliukas 
window.dhx_globalImgPath="img/combobox/";
// pazžiūrim ar atidaryta su ie ar su kitu browseriu
var isNav = (document.all) ? false : true;
var isIE = (document.all) ? true : false;
// funkcija gražina browserio lango ploti
function getWinWidth() {
  if (isNav && !isIE)
    return(window.innerWidth);
  else if (isIE && !isNav)
    return(document.body.clientWidth+13);
  else
    return(100);
}
// var z = null;
var $ = function(el) {
  return document.getElementById(el);
}

//funkcija pakeicia produktų blokų dydį
function rsize() {
  if (isNav && !isIE) {
    // jei ne ie paimam centrinės puslapio dalies potį
    var screen = document.getElementById('products').offsetWidth-14;
  } else if (isIE && !isNav) {
    // jei ie paimam browserio lango plotį ir suskaičiuojam centrinės dalies plotį
    var screen = getWinWidth();
    if (screen<838) screen = 842;
    screen = screen - 15 - 158 - 158 - 43;
  }
  //suskaičiuojam produkto bloko plotį
  var n = Math.floor(screen/210);
  var s = 196+Math.floor((screen-n*210)/n);
  var elems = document.getElementById('products').childNodes;
  // pakeičiam visų produktų blokų pločius atitinkamai
  for (var i=0; i<elems.length; i++) {
    if (elems[i].style) {
      elems[i].style.width = String(Math.round(s))+'px';
    }
  }
}
// nurodom kad funkciją rsize paleistų užkrovus ir pakeitus browserio lango dydį
addEvent(window, 'resize', rsize);
addEvent(window, 'load', rsize);
