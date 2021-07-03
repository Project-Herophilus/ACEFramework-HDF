import store from '@/state/store'

export default [
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/pages/account/login'),
        meta: {
            beforeResolve(routeTo, routeFrom, next) {
                // If the user is already logged in
                if (store.getters['auth/loggedIn']) {
                    // Redirect to the home page instead
                    next({ name: 'home' })
                } else {
                    // Continue to the login page
                    next()
                }
            },
        },
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../views/pages/account/register'),
        meta: {
            beforeResolve(routeTo, routeFrom, next) {
                // If the user is already logged in
                if (store.getters['auth/loggedIn']) {
                    // Redirect to the home page instead
                    next({ name: 'home' })
                } else {
                    // Continue to the login page
                    next()
                }
            },
        },
    },
    {
        path: '/forgot-password',
        name: 'Forgot-password',
        component: () => import('../views/pages/account/forgot-password'),
        meta: {
            beforeResolve(routeTo, routeFrom, next) {
                // If the user is already logged in
                if (store.getters['auth/loggedIn']) {
                    // Redirect to the home page instead
                    next({ name: 'home' })
                } else {
                    // Continue to the login page
                    next()
                }
            },
        },
    },
    {
        path: '/logout',
        name: 'logout',
        meta: {
            authRequired: true,
            beforeResolve(routeTo, routeFrom, next) {
                if (process.env.VUE_APP_DEFAULT_AUTH === "firebase") {
                    store.dispatch('auth/logOut')
                } else {
                    store.dispatch('authfack/logout')
                }
                const authRequiredOnPreviousRoute = routeFrom.matched.some(
                    (route) => route.push('/login')
                )
                // Navigate back to previous page, or home as a fallback
                next(authRequiredOnPreviousRoute ? { name: 'home' } : { ...routeFrom })
            },
        },
    },
    {
        path: '/',
        name: 'home',
        meta: {
            authRequired: true,
        },
        component: () => import('../views/pages/dashboard/index')
    },
    {
        path: '/chat',
        name: 'Chat',
        meta: {
            authRequired: true,
        },
        component: () => import('../views/pages/chat/index')
    },
    {
        path: '/calendar',
        name: 'Calendar',
        meta: {
            authRequired: true,
        },
        component: () => import('../views/pages/calendar/index')
    },
    {
        path: '/ecommerce/products',
        name: 'products',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/products')
    },
    {
        path: '/ecommerce/product-detail',
        name: 'product detail',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/product-detail')
    },
    {
        path: '/ecommerce/orders',
        name: 'Orders',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/orders')
    },
    {
        path: '/ecommerce/customers',
        name: 'Customers',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/customers')
    },
    {
        path: '/ecommerce/cart',
        name: 'Cart',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/cart')
    },
    {
        path: '/ecommerce/checkout',
        name: 'Checkout',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/checkout')
    },
    {
        path: '/ecommerce/shops',
        name: 'Shops',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/shops')
    },
    {
        path: '/ecommerce/add-product',
        name: 'Add-product',
        meta: { authRequired: true },
        component: () => import('../views/pages/ecommerce/add-product')
    },
    {
        path: '/email/inbox',
        name: 'Inbox',
        meta: { authRequired: true },
        component: () => import('../views/pages/email/inbox')
    },
    {
        path: '/email/read',
        name: 'Read Email',
        meta: { authRequired: true },
        component: () => import('../views/pages/email/reademail')
    },
    {
        path: '/auth/login-1',
        name: 'login-1',
        meta: { authRequired: true },
        component: () => import('../views/pages/sample-auth/login-1')
    },
    {
        path: '/auth/register-1',
        name: 'register-1',
        meta: { authRequired: true },
        component: () => import('../views/pages/sample-auth/register-1')
    },
    {
        path: '/auth/lock-screen-1',
        name: 'Lock-screen-1',
        meta: { authRequired: true },
        component: () => import('../views/pages/sample-auth/lock-screen-1')
    },
    {
        path: '/auth/recoverpwd-1',
        name: 'Recoverpwd-1',
        meta: { authRequired: true },
        component: () => import('../views/pages/sample-auth/recoverpwd-1')
    },
    {
        path: '/apps/kanban-board',
        name: 'Kanban-board',
        meta: { authRequired: true },
        component: () => import('../views/pages/kanbanboard/index')
    },
    {
        path: '/pages/starter',
        name: 'Starter Page',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/starter')
    },
    {
        path: '/pages/maintenance',
        name: 'Maintenance',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/maintenance')
    },
    {
        path: '/pages/coming-soon',
        name: 'Coming-soon',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/coming-soon')
    },
    {
        path: '/pages/timeline',
        name: 'Timeline',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/timeline/index')
    },
    {
        path: '/pages/faqs',
        name: 'Faqs',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/faqs')
    },
    {
        path: '/pages/pricing',
        name: 'Pricing',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/pricing/index')
    },
    {
        path: '/pages/error-404',
        name: 'Error-404',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/error-404')
    },
    {
        path: '/pages/error-500',
        name: 'Error-500',
        meta: { authRequired: true },
        component: () => import('../views/pages/utility/error-500')
    },
    {
        path: '/icons/font-awesome',
        name: 'Font Awesome 5',
        meta: { authRequired: true },
        component: () => import('../views/pages/icons/font-awesome/index')
    },
    {
        path: '/icons/dripicons',
        name: 'Dripicons',
        meta: { authRequired: true },
        component: () => import('../views/pages/icons/dripicons')
    },
    {
        path: '/icons/material-design',
        name: 'Material Design',
        meta: { authRequired: true },
        component: () => import('../views/pages/icons/materialdesign/index')
    },
    {
        path: '/icons/remix',
        name: 'Remix Icons',
        meta: { authRequired: true },
        component: () => import('../views/pages/icons/remix/index')
    },
    {
        path: '/ui/buttons',
        name: 'Buttons',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/buttons')
    },
    {
        path: '/ui/alerts',
        name: 'Alerts',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/alerts')
    },
    {
        path: '/ui/grid',
        name: 'Grid',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/grid')
    },
    {
        path: '/ui/cards',
        name: 'Cards',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/cards')
    },
    {
        path: '/ui/carousel',
        name: 'Carousel',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/carousel')
    },
    {
        path: '/ui/dropdowns',
        name: 'Dropdowns',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/dropdowns')
    },
    {
        path: '/ui/images',
        name: 'Images',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/images')
    },
    {
        path: '/ui/modals',
        name: 'Modals',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/modals')
    },
    {
        path: '/ui/rangeslider',
        name: 'Range - slider',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/rangeslider')
    },
    {
        path: '/ui/progressbar',
        name: 'Progressbar',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/progressbars')
    },
    {
        path: '/ui/sweet-alert',
        name: 'Sweet-alert',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/sweet-alert')
    },
    {
        path: '/ui/tabs-accordion',
        name: 'Tabs & Accordion',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/tabs-accordions')
    },
    {
        path: '/ui/typography',
        name: 'Typography',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/typography')
    },
    {
        path: '/ui/video',
        name: 'Video',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/video')
    },
    {
        path: '/ui/general',
        name: 'General',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/general')
    },
    {
        path: '/ui/lightbox',
        name: 'Lightbox',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/lightbox')
    },
    {
        path: '/ui/notification',
        name: 'Notification',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/notification')
    },
    {
        path: '/ui/rating',
        name: 'Rating',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/rating')
    },
    {
        path: '/ui/session-timeout',
        name: 'Session Timeout',
        meta: { authRequired: true },
        component: () => import('../views/pages/ui/session-timeout')
    },
    {
        path: '/form/elements',
        name: 'Form Elements',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/elements')
    },
    {
        path: '/form/validation',
        name: 'Form validation',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/validation')
    },
    {
        path: '/form/advanced',
        name: 'Form Advanced',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/advanced')
    },
    {
        path: '/form/editor',
        name: 'CK Editor',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/ckeditor')
    },
    {
        path: '/form/uploads',
        name: 'File Uploads',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/uploads')
    },
    {
        path: '/form/wizard',
        name: 'Form Wizard',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/wizard')
    },
    {
        path: '/form/mask',
        name: 'Form Mask',
        meta: { authRequired: true },
        component: () => import('../views/pages/forms/mask')
    },
    {
        path: '/tables/basic',
        name: 'Basic Tables',
        meta: { authRequired: true },
        component: () => import('../views/pages/tables/basictable')
    },
    {
        path: '/tables/advanced',
        name: 'Advanced Tables',
        meta: { authRequired: true },
        component: () => import('../views/pages/tables/advancedtable')
    },
    {
        path: '/charts/apex',
        name: 'Apex chart',
        meta: { authRequired: true },
        component: () => import('../views/pages/charts/apex')
    },
    {
        path: '/charts/chartjs',
        name: 'Chartjs chart',
        meta: { authRequired: true },
        component: () => import('../views/pages/charts/chartjs/index')
    },
    {
        path: '/charts/chartist',
        name: 'Chartist chart',
        meta: { authRequired: true },
        component: () => import('../views/pages/charts/chartist')
    },
    {
        path: '/charts/echart',
        name: 'Echart chart',
        meta: { authRequired: true },
        component: () => import('../views/pages/charts/echart/index')
    },
    {
        path: '/maps/google',
        name: 'Google Maps',
        meta: { authRequired: true },
        component: () => import('../views/pages/maps/google')
    },
    {
        path: '/maps/leaflet',
        name: 'Leaflet Maps',
        meta: { authRequired: true },
        component: () => import('../views/pages/maps/leaflet/index')
    },
]
