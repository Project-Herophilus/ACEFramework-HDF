export const menuItems = [
    {
        id: 1,
        label: "menuitems.menu.text",
        isTitle: true
    },
    {
        id: 2,
        label: 'menuitems.dashboard.text',
        icon: 'ri-dashboard-line',
        badge: {
            variant: "success",
            text: "menuitems.dashboard.badge"
        },
        link: '/'
    },
    {
        id: 3,
        label: 'menuitems.calendar.text',
        icon: 'ri-calendar-2-line',
        link: '/calendar'
    },
    {
        id: 4,
        label: 'menuitems.chat.text',
        icon: 'ri-chat-1-line',
        link: '/chat'
    },
    {
        id: 5,
        label: 'menuitems.ecommerce.text',
        icon: 'ri-store-2-line',
        subItems: [
            {
                id: 6,
                label: 'menuitems.ecommerce.list.products',
                link: '/ecommerce/products'
            },
            {
                id: 7,
                label: 'menuitems.ecommerce.list.productdetail',
                link: '/ecommerce/product-detail'
            },
            {
                id: 8,
                label: 'menuitems.ecommerce.list.orders',
                link: '/ecommerce/orders'
            },
            {
                id: 9,
                label: 'menuitems.ecommerce.list.customers',
                link: '/ecommerce/customers'
            },
            {
                id: 10,
                label: 'menuitems.ecommerce.list.cart',
                link: '/ecommerce/cart'
            },
            {
                id: 11,
                label: 'menuitems.ecommerce.list.checkout',
                link: '/ecommerce/checkout'
            },
            {
                id: 12,
                label: 'menuitems.ecommerce.list.shops',
                link: '/ecommerce/shops'
            },
            {
                id: 13,
                label: 'menuitems.ecommerce.list.addproduct',
                link: '/ecommerce/add-product'
            }
        ]
    },
    {
        id: 14,
        label: 'menuitems.email.text',
        icon: 'ri-mail-send-line',
        subItems: [
            {
                id: 15,
                label: 'menuitems.email.list.inbox',
                link: '/email/inbox'
            },
            {
                id: 16,
                label: 'menuitems.email.list.reademail',
                link: '/email/read'
            }
        ]
    },
    {
        id: 17,
        label: 'menuitems.kanban.text',
        icon: 'ri-artboard-2-line',
        link: '/apps/kanban-board'
    },
    {
        id: 18,
        isLayout: true
    },
    {
        id: 19,
        label: 'menuitems.pages.text',
        isTitle: true
    },
    {
        id: 20,
        label: 'menuitems.authentication.text',
        icon: 'ri-account-circle-line',
        subItems: [
            {
                id: 21,
                label: 'menuitems.authentication.list.login',
                link: '/auth/login-1'
            },
            {
                id: 22,
                label: 'menuitems.authentication.list.register',
                link: '/auth/register-1'
            },
            {
                id: 23,
                label: 'menuitems.authentication.list.recoverpwd',
                link: '/auth/recoverpwd-1'
            },
            {
                id: 24,
                label: 'menuitems.authentication.list.lockscreen',
                link: '/auth/lock-screen-1'
            }
        ]
    },
    {
        id: 25,
        label: 'menuitems.utility.text',
        icon: 'ri-profile-line',
        subItems: [
            {
                id: 26,
                label: 'menuitems.utility.list.starter',
                link: '/pages/starter'
            },
            {
                id: 27,
                label: 'menuitems.utility.list.maintenance',
                link: '/pages/maintenance'
            },
            {
                id: 28,
                label: 'menuitems.utility.list.comingsoon',
                link: '/pages/coming-soon'
            },
            {
                id: 29,
                label: 'menuitems.utility.list.timeline',
                link: '/pages/timeline'
            },
            {
                id: 30,
                label: 'menuitems.utility.list.faqs',
                link: '/pages/faqs'
            },
            {
                id: 31,
                label: 'menuitems.utility.list.pricing',
                link: '/pages/pricing'
            },
            {
                id: 32,
                label: 'menuitems.utility.list.error404',
                link: '/pages/error-404'
            },
            {
                id: 33,
                label: 'menuitems.utility.list.error500',
                link: '/pages/error-500'
            },
        ]
    },
    {
        id: 36,
        label: 'menuitems.components.text',
        isTitle: true
    },
    {
        id: 37,
        label: 'menuitems.uielements.text',
        icon: 'ri-pencil-ruler-2-line',
        subItems: [
            {
                id: 38,
                label: 'menuitems.uielements.list.alerts',
                link: '/ui/alerts'
            },
            {
                id: 39,
                label: 'menuitems.uielements.list.buttons',
                link: '/ui/buttons'
            },
            {
                id: 40,
                label: 'menuitems.uielements.list.cards',
                link: '/ui/cards'
            },
            {
                id: 41,
                label: 'menuitems.uielements.list.carousel',
                link: '/ui/carousel'
            },
            {
                id: 42,
                label: 'menuitems.uielements.list.dropdowns',
                link: '/ui/dropdowns'
            },
            {
                id: 43,
                label: 'menuitems.uielements.list.grid',
                link: '/ui/grid'
            },
            {
                id: 44,
                label: 'menuitems.uielements.list.images',
                link: '/ui/images'
            },
            {
                id: 45,
                label: 'menuitems.uielements.list.lightbox',
                link: '/ui/lightbox'
            }, {
                id: 46,
                label: 'menuitems.uielements.list.modals',
                link: '/ui/modals'
            }, {
                id: 47,
                label: 'menuitems.uielements.list.rangeslider',
                link: '/ui/rangeslider'
            }, {
                id: 48,
                label: 'menuitems.uielements.list.sessiontimeout',
                link: '/ui/session-timeout'
            },
            {
                id: 49,
                label: 'menuitems.uielements.list.progressbar',
                link: '/ui/progressbar'
            }, {
                id: 50,
                label: 'menuitems.uielements.list.sweetalert',
                link: '/ui/sweet-alert'
            }, {
                id: 51,
                label: 'menuitems.uielements.list.tabs',
                link: '/ui/tabs-accordion'
            }, {
                id: 52,
                label: 'menuitems.uielements.list.typography',
                link: '/ui/typography'
            }, {
                id: 53,
                label: 'menuitems.uielements.list.video',
                link: '/ui/video'
            }, {
                id: 54,
                label: 'menuitems.uielements.list.general',
                link: '/ui/general'
            }, {
                id: 55,
                label: 'menuitems.uielements.list.rating',
                link: '/ui/rating'
            }, {
                id: 56,
                label: 'menuitems.uielements.list.notifications',
                link: '/ui/notification'
            }
        ]
    },
    {
        id: 57,
        label: 'menuitems.forms.text',
        icon: 'ri-eraser-fill',
        badge: {
            variant: 'danger',
            text: 'menuitems.forms.badge'
        },
        subItems: [
            {
                id: 58,
                label: 'menuitems.forms.list.elements',
                link: '/form/elements'
            },
            {
                id: 59,
                label: 'menuitems.forms.list.validation',
                link: '/form/validation'
            },
            {
                id: 60,
                label: 'menuitems.forms.list.advanced',
                link: '/form/advanced'
            },
            {
                id: 61,
                label: 'menuitems.forms.list.editor',
                link: '/form/editor'
            },
            {
                id: 62,
                label: 'menuitems.forms.list.fileupload',
                link: '/form/uploads'
            },
            {
                id: 63,
                label: 'menuitems.forms.list.wizard',
                link: '/form/wizard'
            },
            {
                id: 64,
                label: 'menuitems.forms.list.mask',
                link: '/form/mask'
            }
        ]
    },
    {
        id: 65,
        label: 'menuitems.tables.text',
        icon: 'ri-table-2',
        subItems: [
            {
                id: 66,
                label: 'menuitems.tables.list.basic',
                link: '/tables/basic'
            },
            {
                id: 67,
                label: 'menuitems.tables.list.advanced',
                link: '/tables/advanced'
            },
            // {
            //     id: 68,
            //     label: 'Responsive Table',
            //     link: ''
            // },
        ]
    },
    {
        id: 69,
        label: 'menuitems.charts.text',
        icon: 'ri-bar-chart-line',
        subItems: [
            {
                id: 70,
                label: 'menuitems.charts.list.apex',
                link: '/charts/apex'
            },
            {
                id: 71,
                label: "menuitems.charts.list.chartjs",
                link: '/charts/chartjs'
            },
            {
                id: 72,
                label: "menuitems.charts.list.chartist",
                link: '/charts/chartist'
            },
            {
                id: 73,
                label: "menuitems.charts.list.echart",
                link: '/charts/echart'
            }
        ]
    },
    {
        id: 74,
        label: "menuitems.icons.text",
        icon: 'ri-brush-line',
        subItems: [
            {
                id: 75,
                label: 'menuitems.icons.list.remix',
                link: '/icons/remix'
            },
            {
                id: 76,
                label: "menuitems.icons.list.materialdesign",
                link: '/icons/material-design'
            },
            {
                id: 77,
                label: "menuitems.icons.list.dripicons",
                link: '/icons/dripicons'
            },
            {
                id: 78,
                label: "menuitems.icons.list.fontawesome",
                link: '/icons/font-awesome'
            }
        ]
    },
    {
        id: 79,
        label: "menuitems.maps.text",
        icon: 'ri-map-pin-line',
        subItems: [
            {
                id: 80,
                label: "menuitems.maps.list.googlemap",
                link: '/maps/google'
            },
            {
                id: 81,
                label: 'menuitems.maps.list.leaflet',
                link: '/maps/leaflet'
            }
        ]
    }
]