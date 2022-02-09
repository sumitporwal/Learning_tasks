import { createStore } from 'redux'
import todosReducer from './reducer'

const initialState = {
    todoList : ["clean the house","buy milk"]
}

const store = createStore(todosReducer,initialState)

export default store;