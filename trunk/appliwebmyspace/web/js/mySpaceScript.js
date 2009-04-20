var tempsDebut;
function file(fichier)
{
    if(window.XMLHttpRequest) // FIREFOX
        xhr_object = new XMLHttpRequest();
    else if(window.ActiveXObject) // IE
        xhr_object = new ActiveXObject("Microsoft.XMLHTTP");
    else
        return(false);
    xhr_object.open("GET", fichier, true);
    xhr_object.send(null);
    xhr_object.onreadystatechange = function(){
        if(xhr_object.readyState == 4) init2(xhr_object.responseText);
    }
// We still need to write some code here
}

function sentInfo(){
    if(document.getElementById("idInput").value!=""){
        init(document.getElementById("idInput").value,document.getElementById("profSelect"),document.getElementById("niveauSelect"),document.getElementById("numberCheck"));
    }
}

function init(id,profondeur,niveau,number){
    tempsDebut = new Date();
    document.getElementById("infovis").innerHTML = "<img src='images/loading.gif'>";
    document.getElementById("btnClick").disabled="disabled";
    file("Extraction?id="+id+"&prof="+profondeur.value+"&niveau="+niveau.value+"&number="+number.value);
}

function ecrireInfoAmi(id,nom, nbAmis){
    document.getElementById("infos").innerHTML = "<h2>Informations du profil</h2>";
    document.getElementById("infos").innerHTML += "<ul>";
    document.getElementById("infos").innerHTML += "<li><u>Nom du profil</u> : <b>"+nom+"</b></li>";
    document.getElementById("infos").innerHTML += "<li><u>ID du profil</u> : <b>"+id+"</b></li>";
    document.getElementById("infos").innerHTML += "<li><u>Nombre total d'amis</u> : <b>"+nbAmis+"</b></li>";
    document.getElementById("infos").innerHTML += "<li>-> <a href='http://www.myspace.com/"+id+"' target='_blank'>Aller sur son profil</a></li>";
    document.getElementById("infos").innerHTML += "</ul>";
}

function ecrireTempsTraitement(){
    tempsFin = new Date();
    tempsTraitement = tempsFin-tempsDebut;
    document.getElementById("tempsTraitement").innerHTML = "Généré en "+tempsTraitement/1000+" secondes";
}

function init2(json) {
    if(json.match("ne correspond à aucun profil")){
        document.getElementById("infovis").innerHTML = "L'ID ne correspond à aucun profil dans MySpace";
        document.getElementById("btnClick").disabled="";
        return false;
    } else if (json==""){
        document.getElementById("infovis").innerHTML = "Son profil est privé...";
        document.getElementById("btnClick").disabled="";
        return false;
    }
    document.getElementById("infovis").innerHTML = "";
    //Set node interpolation to linear (can also be 'polar')
    Config.interpolation = "linear";
    //Set distance for concentric circles
    Config.levelDistance = 100;
    var json = eval ( "(" + json + ")");
    ecrireInfoAmi(json.id,json.name,json.nbAmis);
    ecrireTempsTraitement();
  
    //Create a new canvas instance.
    var randomnumber=Math.floor(Math.random()*1000);
    var nomCanvas = "mycanvas"+randomnumber;
    var canvas = new Canvas(nomCanvas, {
        //Where to inject the canvas. Any div container will do.
        'injectInto':'infovis',
        //width and height for canvas. Default's to 200.
        'width': 500,
        'height':400,
        //Canvas styles
        'styles': {
            'fillStyle': '#ccddee',
            'strokeStyle': '#772277'
        },
        //Add a background canvas for plotting
        //concentric circles.
        'backgroundCanvas': {
            //Add Canvas styles for the bck canvas.
            'styles': {
                'fillStyle': '#444',
                'strokeStyle': '#444'
            },
            //Add the initialization and plotting functions.
            'impl': {
                'init': $empty,
                'plot': function(canvas, ctx) {
                    var times = 6, d = Config.levelDistance;
                    var pi2 = Math.PI*2;
                    for(var i=1; i<=times; i++) {
                        ctx.beginPath();
                        ctx.arc(0, 0, i * d, 0, pi2, true);
                        ctx.stroke();
                        ctx.closePath();
                    }
                }
            }
        }
    });
  
    var rgraph= new RGraph(canvas,  {
        //Add a controller to make the tree move on click.
        onCreateLabel: function(domElement, node) {
            var d = $(domElement);
            d.set('html', node.name).addEvents({
                'click': function() {
                    rgraph.onClick(d.id);
                }
            });
        },
  
        //Take off previous width and height styles and
        //add half of the *actual* label width to the left position
        // That will center your label (do the math man).
        onPlaceLabel: function(domElement, node) {
            domElement.innerHTML = '';
            domElement.innerHTML = node.name;
            var left = parseInt(domElement.style.left);
            domElement.style.width = '';
            domElement.style.height = '';
            var w = domElement.offsetWidth;
            domElement.style.left = (left - w /2) + 'px';
        }
  
    });
  
    //load tree from tree data.  
    rgraph.loadTreeFromJSON(json);  
    //compute positions  
    rgraph.compute();  
    //make first plot  
    rgraph.plot();  
}