import { createContext, useState, useContext, useEffect } from "react"
import { STORAGE_KEY, axios } from "../axios/axios"

const AuthContext = createContext(null)

export default function AuthContextProvider({children}) {
    const [auth, setAuth] = useState(null)
    const [loaded, setLoaded] = useState(false)

    useEffect(() => {
        axios.post("/api/authorize")
            .then((response) => {
                if(response.status == 200) {
                    setAuth(response.data.username)
                    localStorage.setItem(STORAGE_KEY, response.data.token)
                } else {
                    localStorage.removeItem(STORAGE_KEY)
                }
            })
            .catch(() => {
                localStorage.removeItem(STORAGE_KEY)
            })
            .finally(() => {
                setLoaded(true)
            })
    }, [])

    if(!loaded) return null

    return (
        <AuthContext.Provider value={[auth, setAuth]}>
            {children}
        </AuthContext.Provider>
    )
}

export function useAuth() {
    return useContext(AuthContext)
}