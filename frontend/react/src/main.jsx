import React from 'react'
import ReactDOM from 'react-dom/client'
import Customer from './Customer.jsx'
import {ChakraProvider} from '@chakra-ui/react'
import {createStandaloneToast} from '@chakra-ui/react'
import './index.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Login from "./components/login/Login.jsx";
import AuthProvider from "./components/context/AuthContext.jsx";
import ProtectedRoute from "./components/shared/ProtectedRoute.jsx";
import Signup from "./components/signup/Signup.jsx";
import Home from "./Home.jsx";
import Todos from "./components/todos/Todos.jsx";

const router = createBrowserRouter(
    [
        {
            path: "/",
            element: <Login/>
        },
        {
            path: "/signup",
            element: <Signup/>
        },
        {
            path: "todos",
            element: <ProtectedRoute><Todos/></ProtectedRoute>
        },
        {
            path: "dashboard",
            element: <ProtectedRoute><Home/></ProtectedRoute>
        },
        {
            path: "settings",
            element: <ProtectedRoute><h1>Settings soon</h1></ProtectedRoute>
        },
        {
            path: "dashboard/customers",
            element: <ProtectedRoute><Customer/></ProtectedRoute>
        }
    ])
const {ToastContainer} = createStandaloneToast()

ReactDOM.createRoot(document.getElementById('root'))
    .render(
        <React.StrictMode>
            <ChakraProvider>
                <AuthProvider>
                    <RouterProvider router={router}/>
                </AuthProvider>
                <ToastContainer/>
            </ChakraProvider>
        </React.StrictMode>,
    )
