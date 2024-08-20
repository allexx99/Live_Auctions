// import React, { useEffect, useState } from 'react';
// import * as SockJS from 'sockjs-client';
// import * as Stomp from 'stompjs';

// const NotificationsComponent = () => {


//   const [notifications, setNotifications] = useState([]);

//   useEffect(() => {
//     const ws = new SockJS('http://localhost:8080/topic/socket/newPost');

//     console.log("In Connect");

//     ws.onopen = () => {
//       console.log('WebSocket connection established.');
//     };

//     ws.onmessage = (event) => {
//       const receivedNotification = JSON.parse(event.data);
//       setNotifications((prevNotifications) => [...prevNotifications, receivedNotification]);
//     };

//     ws.onclose = () => {
//       console.log('WebSocket connection closed.');
//     };

//   //   return () => {
//   //     // Clean up the WebSocket connection
//   //     ws.close();
//   //   };
//   // }, []); 
//   })
//   // useEffect(() => {
//   //   console.log("In Connect");
//   //   const URL = "http://localhost:8080/socket";
//   //   const websocket = new SockJS(URL);
//   //   const stompClient = Stomp.over(websocket);
//   //   stompClient.connect({}, frame => {
//   //       stompClient.subscribe("/topic/socket/newPost", notification => {
//   //           let message = notification.body;
//   //           console.log(message);
//   //           alert(message);

//   //       })
//   //   })
//   // }, [])

//   return ( 
//     <div>
//       <h2>Notifications</h2>
//       <ul>
//         {notifications.map((notification, index) => (
//           <li key={index}>{notification.message}</li>
//         ))}
//       </ul>
//     </div>
//    );
// }

// export default NotificationsComponent;