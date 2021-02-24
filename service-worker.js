var cacheName = 'cache_1614161086186';

var filesToCache = [  
'/gmd-amcharts4-demo/',

'GmdAmCharts4Demo/D57D317B7273D9090538BB3F850615FE.cache.js',

'GmdAmCharts4Demo/GmdAmCharts4Demo.devmode.js',

'GmdAmCharts4Demo/GmdAmCharts4Demo.nocache.js',

'GmdAmCharts4Demo/clear.cache.gif',

'GmdAmCharts4Demo/css/animation.css',

'GmdAmCharts4Demo/css/animation.min.css',

'GmdAmCharts4Demo/css/fontawesome-all.css',

'GmdAmCharts4Demo/css/fontawesome-all.min.css',

'GmdAmCharts4Demo/css/material-icons.css',

'GmdAmCharts4Demo/css/material-icons.min.css',

'GmdAmCharts4Demo/css/materialize.blue.css',

'GmdAmCharts4Demo/css/materialize.blue.min.css',

'GmdAmCharts4Demo/css/materialize.css',

'GmdAmCharts4Demo/css/materialize.min.css',

'GmdAmCharts4Demo/font/material-icons/MaterialIcons-Regular.eot',

'GmdAmCharts4Demo/font/material-icons/MaterialIcons-Regular.ttf',

'GmdAmCharts4Demo/font/material-icons/MaterialIcons-Regular.woff',

'GmdAmCharts4Demo/font/material-icons/MaterialIcons-Regular.woff2',

'GmdAmCharts4Demo/fontawesome-icons/fa-brands-400.eot',

'GmdAmCharts4Demo/fontawesome-icons/fa-brands-400.ttf',

'GmdAmCharts4Demo/fontawesome-icons/fa-brands-400.woff',

'GmdAmCharts4Demo/fontawesome-icons/fa-brands-400.woff2',

'GmdAmCharts4Demo/fontawesome-icons/fa-regular-400.eot',

'GmdAmCharts4Demo/fontawesome-icons/fa-regular-400.ttf',

'GmdAmCharts4Demo/fontawesome-icons/fa-regular-400.woff',

'GmdAmCharts4Demo/fontawesome-icons/fa-regular-400.woff2',

'GmdAmCharts4Demo/fontawesome-icons/fa-solid-900.eot',

'GmdAmCharts4Demo/fontawesome-icons/fa-solid-900.ttf',

'GmdAmCharts4Demo/fontawesome-icons/fa-solid-900.woff',

'GmdAmCharts4Demo/fontawesome-icons/fa-solid-900.woff2',

'index.html',

'launcher-icons/launcher1x.png',

'launcher-icons/launcher2x.png',

'launcher-icons/launcher4x.png',

'launcher-icons/launcher5x.png',

'splash/font/Roboto-Regular.eot',

'splash/font/Roboto-Regular.ttf',

'splash/font/Roboto-Regular.woff',

'splash/font/Roboto-Regular.woff2',

'splash/splash.css',

'splash/splash.js'
    ];


        /**
         * The install event is your chance to cache everything you need before being able to control clients. The promise you
         * pass to event.waitUntil() lets the browser know when your install completes, and if it was successful.
         */
        self.addEventListener('install', e => {
            e.waitUntil(
                caches.open(cacheName).then(cache => {
                    return cache.addAll(filesToCache)
                        .then(() => self.skipWaiting());
    })
    );
    });

        /**
         * Once your service worker is ready to control clients and handle functional events like push and sync, you'll get an
         * activate event. But that doesn't mean the page that called .register() will be controlled.
         */
        self.addEventListener('activate', event => {
            event.waitUntil(
                caches.keys().then(function(cacheNames) {
                    return Promise.all(
                        cacheNames.map(function(oldCache) {
                            if (oldCache !== cacheName) {
                                console.log('ServiceWorker : Deleting old cache:', oldCache);
                                return caches.delete(oldCache);
                            }
                        })
                    );
                }).then(function() {
                    console.log('ServiceWorker : Claiming clients for version', cacheName);
                    return self.clients.claim();
                })
            );
    });

        /**
         * It contains information about the fetch, including the request and how the receiver will treat the response.
         * It provides the event.respondWith() method, which allows us to provide a response to this fetch.
         */
        self.addEventListener('fetch', event => {
            if (event.request.method !== 'GET') {
            /* If we don't block the event as shown below, then the request will go to
               the network as usual.
            */
            return;
        }
        event.respondWith(
            caches.open(cacheName)
                .then(cache => cache.match(event.request, {ignoreSearch: true}))
            .then(response => {
            return response || fetch(event.request);
    })
    );
    });