function init() {  
  //Set node interpolation to linear (can also be 'polar')  
  Config.interpolation = "linear";  
  //Set distance for concentric circles  
  Config.levelDistance = 100;  
  
  var json = {"id":"node02",  
 "name":"0.2",  
 "data":[  
    {"key":"key1",  
     "value":8},  
    {"key":"key2",  
     "value":-88}],  
 "children":[  
    {"id":"node13",  
     "name":"1.3",  
     "data":[  
        {"key":"key1",  
         "value":8},  
        {"key":"key2",  
         "value":74}],  
     "children":[  
        {"id":"node24",  
         "name":"2.4",  
         "data":[  
            {"key":"key1",  
             "value":10},  
            {"key":"key2",  
             "value":55}],  
         "children":[]},  
        {"id":"node25",  
         "name":"2.5",  
         "data":[  
            {"key":"key1",  
             "value":8},  
            {"key":"key2",  
             "value":67}],  
         "children":[]},  
        {"id":"node26",  
         "name":"2.6",  
         "data":[  
            {"key":"key1",  
             "value":5},  
            {"key":"key2",  
             "value":-50}],  
         "children":[]},  
        {"id":"node27",  
         "name":"2.7",  
         "data":[  
            {"key":"key1",  
             "value":8},  
            {"key":"key2",  
             "value":10}],  
         "children":[]},  
        {"id":"node28",  
         "name":"2.8",  
         "data":[  
            {"key":"key1",  
             "value":2},  
            {"key":"key2",  
             "value":-69}],  
         "children":[]},  
        {"id":"node29",  
         "name":"2.9",  
         "data":[  
            {"key":"key1",  
             "value":3},  
            {"key":"key2",  
             "value":98}],  
         "children":[]},  
        {"id":"node210",  
         "name":"2.10",  
         "data":[  
            {"key":"key1",  
             "value":6},  
            {"key":"key2",  
             "value":12}],  
         "children":[]},  
        {"id":"node211",  
         "name":"2.11",  
         "data":[  
            {"key":"key1",  
             "value":2},  
            {"key":"key2",  
             "value":-95}],  
         "children":[]}]},  
    {"id":"node112",  
     "name":"1.12",  
     "data":[  
        {"key":"key1",  
         "value":1},  
        {"key":"key2",  
         "value":96}],  
     "children":[  
        {"id":"node213",  
         "name":"2.13",  
         "data":[  
            {"key":"key1",  
             "value":6},  
            {"key":"key2",  
             "value":-58}],  
         "children":[]},  
        {"id":"node214",  
         "name":"2.14",  
         "data":[  
            {"key":"key1",  
             "value":9},  
            {"key":"key2",  
             "value":-42}],  
         "children":[]},  
        {"id":"node215",  
         "name":"2.15",  
         "data":[  
            {"key":"key1",  
             "value":10},  
            {"key":"key2",  
             "value":92}],  
         "children":[]},  
        {"id":"node216",  
         "name":"2.16",  
         "data":[  
            {"key":"key1",  
             "value":7},  
            {"key":"key2",  
             "value":-15}],  
         "children":[]},  
        {"id":"node217",  
         "name":"2.17",  
         "data":[  
            {"key":"key1",  
             "value":3},  
            {"key":"key2",  
             "value":29}],  
         "children":[]},  
        {"id":"node218",  
         "name":"2.18",  
         "data":[  
            {"key":"key1",  
             "value":8},  
            {"key":"key2",  
             "value":-59}],  
         "children":[]},  
        {"id":"node219",  
         "name":"2.19",  
         "data":[  
            {"key":"key1",  
             "value":3},  
            {"key":"key2",  
             "value":21}],  
         "children":[]},  
        {"id":"node220",  
         "name":"2.20",  
         "data":[  
            {"key":"key1",  
             "value":2},  
            {"key":"key2",  
             "value":78}],  
         "children":[]}]},  
    {"id":"node121",  
     "name":"1.21",  
     "data":[  
        {"key":"key1",  
         "value":3},  
        {"key":"key2",  
         "value":53}],  
     "children":[  
        {"id":"node222",  
         "name":"2.22",  
         "data":[  
            {"key":"key1",  
             "value":5},  
            {"key":"key2",  
             "value":10}],  
         "children":[]},  
        {"id":"node223",  
         "name":"2.23",  
         "data":[  
            {"key":"key1",  
             "value":10},  
            {"key":"key2",  
             "value":21}],  
         "children":[]},  
        {"id":"node224",  
         "name":"2.24",  
         "data":[  
            {"key":"key1",  
             "value":6},  
            {"key":"key2",  
             "value":-32}],  
         "children":[]},  
        {"id":"node225",  
         "name":"2.25",  
         "data":[  
            {"key":"key1",  
             "value":5},  
            {"key":"key2",  
             "value":-42}],  
         "children":[]},  
        {"id":"node226",  
         "name":"2.26",  
         "data":[  
            {"key":"key1",  
             "value":2},  
            {"key":"key2",  
             "value":75}],  
         "children":[]},  
        {"id":"node227",  
         "name":"2.27",  
         "data":[  
            {"key":"key1",  
             "value":1},  
            {"key":"key2",  
             "value":-74}],  
         "children":[]},  
        {"id":"node228",  
         "name":"2.28",  
         "data":[  
            {"key":"key1",  
             "value":2},  
            {"key":"key2",  
             "value":52}],  
         "children":[]},  
        {"id":"node229",  
         "name":"2.29",  
         "data":[  
            {"key":"key1",  
             "value":10},  
            {"key":"key2",  
             "value":-49}],  
         "children":[]}]},  
    {"id":"node130",  
     "name":"1.30",  
     "data":[  
        {"key":"key1",  
         "value":9},  
        {"key":"key2",  
         "value":-29}],  
     "children":[  
        {"id":"node231",  
         "name":"2.31",  
         "data":[  
            {"key":"key1",  
             "value":6},  
            {"key":"key2",  
             "value":-23}],  
         "children":[]},  
        {"id":"node232",  
         "name":"2.32",  
         "data":[  
            {"key":"key1",  
             "value":10},  
            {"key":"key2",  
             "value":19}],  
         "children":[]},  
        {"id":"node233",  
         "name":"2.33",  
         "data":[  
            {"key":"key1",  
             "value":1},  
            {"key":"key2",  
             "value":92}],  
         "children":[]}]},  
    {"id":"node134",  
     "name":"1.34",  
     "data":[  
        {"key":"key1",  
         "value":9},  
        {"key":"key2",  
         "value":71}],  
     "children":[  
        {"id":"node235",  
         "name":"2.35",  
         "data":[  
            {"key":"key1",  
             "value":5},  
            {"key":"key2",  
             "value":-65}],  
         "children":[]}]},  
    {"id":"node136",  
     "name":"1.36",  
     "data":[  
        {"key":"key1",  
         "value":3},  
        {"key":"key2",  
         "value":-11}],  
     "children":[  
        {"id":"node237",  
         "name":"2.37",  
         "data":[  
            {"key":"key1",  
             "value":6},  
            {"key":"key2",  
             "value":-85}],  
         "children":[]},  
        {"id":"node238",  
         "name":"2.38",  
         "data":[  
            {"key":"key1",  
             "value":3},  
            {"key":"key2",  
             "value":-13}],  
         "children":[]},  
        {"id":"node239",  
         "name":"2.39",  
         "data":[  
            {"key":"key1",  
             "value":1},  
            {"key":"key2",  
             "value":80}],  
         "children":[]},  
        {"id":"node240",  
         "name":"2.40",  
         "data":[  
            {"key":"key1",  
             "value":10},  
            {"key":"key2",  
             "value":-69}],  
         "children":[]}]},  
    {"id":"node141",  
     "name":"1.41",  
     "data":[  
        {"key":"key1",  
         "value":10},  
        {"key":"key2",  
         "value":-4}],  
     "children":[  
        {"id":"node242",  
         "name":"2.42",  
         "data":[  
            {"key":"key1",  
             "value":8},  
            {"key":"key2",  
             "value":-27}],  
         "children":[]},  
        {"id":"node243",  
         "name":"2.43",  
         "data":[  
            {"key":"key1",  
             "value":9},  
            {"key":"key2",  
             "value":-44}],  
         "children":[]},  
        {"id":"node244",  
         "name":"2.44",  
         "data":[  
            {"key":"key1",  
             "value":9},  
            {"key":"key2",  
             "value":24}],  
         "children":[]},  
        {"id":"node245",  
         "name":"2.45",  
         "data":[  
            {"key":"key1",  
             "value":8},  
            {"key":"key2",  
             "value":-66}],  
         "children":[]}]}]};//data defined previously  
  
  //Create a new canvas instance.  
  var canvas = new Canvas('mycanvas', {  
    //Where to inject the canvas. Any div container will do.  
    'injectInto':'infovis',  
    //width and height for canvas. Default's to 200.  
    'width': 500,  
    'height':300,  
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