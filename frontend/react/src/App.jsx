import UserProfile from "./UserProfile.jsx";
import {useState, useEffect} from "react";

const users = [
    {
        name: "Jamila",
        age: 22,
        gender: "FEMALE"
    }, {
        name: "Marco",
        age: 24,
        gender: "MALE"
    }
    , {
        name: "Flaminco",
        age: 27,
        gender: "MALE"
    }
]

const UserProfiles = ({users}) => (
    <div>
        {users.map((user, index) => (
            <UserProfile
                key={index}
                name={user.name}
                age={user.age}
                gender={user.gender}
                imageNumber={index}
            />
        ))}
    </div>
)

function App() {

    const [counter, setCounter] = useState(0);
    const [isLoading, setIsLoading] = useState(false)
    console.log("before useeffect")
    useEffect(() => {
        console.log("in useEffect")
        setIsLoading(true)
        setTimeout(() => {
            setIsLoading(false)
            console.log("inside timeout")
        }, 4000)
        return () => {
            console.log("cleanup function")
        }
    }, [])
    console.log("after useeffect")
    if (isLoading) {
        return "loading..."
    }
    return (
        <div>
            <button onClick={() => {
                setCounter(prevState => prevState + 1)
            }}>Increment counter</button>
            <h1>{counter}</h1>
            <UserProfiles users={users}/>
        </div>
    );
}

export default App
