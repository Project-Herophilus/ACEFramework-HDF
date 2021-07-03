const chatData = [
    {
        id: 1,
        image: require('@/assets/images/users/avatar-2.jpg'),
        name: 'Frank Vickery',
        message: 'Hey! there I\'m available',
        time: '04    min',
        status: 'online'
    },
    {
        id: 2,
        image: require('@/assets/images/users/avatar-3.jpg'),
        name: 'Robert Winter',
        message: 'I\'ve finished it! See you so',
        time: '09 min',
        status: 'away'
    },
    {
        id: 3,
        name: 'Crystal Elliott',
        message: 'This theme is awesome!',
        time: '21 min',
        status: 'online'
    },
    {
        id: 4,
        image: require('@/assets/images/users/avatar-4.jpg'),
        name: 'Kristen Steele',
        message: 'Nice to meet you',
        time: '1 hr',
    },
    {
        id: 5,
        name: 'Mitchel Givens',
        message: 'Hey! there I\'m available',
        time: '3 hrs',
        status: 'away'
    },
    {
        id: 6,
        image: require('@/assets/images/users/avatar-6.jpg'),
        name: 'Stephen Hadley',
        message: 'I\'ve finished it! See you so',
        time: '5 hrs',
        status: 'online'
    },
    {
        id: 7,
        image: require('@/assets/images/users/avatar-2.jpg'),
        name: 'Tracy Penley',
        message: 'This theme is awesome!',
        time: '24/03',
        status: 'online'
    },
];  

const chatMessagesData = [
    {
        name: 'Frank Vickery',
        image: require('@/assets/images/users/avatar-2.jpg'),
        message: 'Hello!',
        time: '10:00'
    },
    {
        align: 'right',
        name: 'Henry Wells',
        message: 'Hi, How are you? What about our next meeting?',
        time: '10:02'
    },
    {
        name: 'Frank Vickery',
        image: require('@/assets/images/users/avatar-2.jpg'),
        message: 'Yeah everything is fine',
        time: '10:06'
    },
    {
        name: 'Frank Vickery',
        image: require('@/assets/images/users/avatar-2.jpg'),
        message: '& Next meeting tomorrow 10.00AM',
        time: '10:06'
    },
    {
        align: 'right',
        name: 'Henry Wells',
        message: 'Wow that\'s great',
        time: '10:07'
    }
];

export { chatData, chatMessagesData };