import React from "react";

const initialState = {
    todoList : ["clean the house","buy milk"]
}

export default function todosReducer(state = initialState, action) {
    switch (action.type) {
      case 'todos/todoAdded': {
        return {
          ...state,
          todoList : [
              ...state.todoList,
              action.payload,
          ],
        }
      }
      case 'todos/todoDeleted': {
        return {
            ...state,
            todoList : state.todoList.filter((todo) => todo !== action.payload)
        }
      }

      default:
        return state
    }
  }
  