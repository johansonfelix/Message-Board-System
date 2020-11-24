self.addEventListener('install', function(e) {
    e.waitUntil(
        caches.open('msgb-store').then(function(cache) {
            return cache.addAll([
                '/index.html',
                '/index.js',
                '/logo.png',
                '/icon.png',
                '/avatar.png'

            ]);
        })
    );
});

self.addEventListener('fetch', function(e) {
    console.log(e.request.url);
    e.respondWith(
        caches.match(e.request).then(function(response) {
            return response || fetch(e.request);
        })
    );
});