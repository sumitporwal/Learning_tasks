import React, { useState } from "react";
import { useDispatch } from 'react-redux'
import './App.css';

function CreateTodo({ addTodo }) {
    const [todo, setToDo] = useState("");
    const dispatch = useDispatch();

    const handleChange = e => setToDo(e.target.value)

    const handleKeyPress = e => {
      const trimmedText = e.target.value.trim()
      if (e.key === 'Enter' && trimmedText) {
        dispatch({ type: 'todos/todoAdded', payload: trimmedText })
        setToDo('')
      }
  }
  
    return (
      <div>
        <input
          type="text"
          className="input"
          value={todo}
          onChange={e => handleChange(e)}
          onKeyPress={handleKeyPress}
        />
        <button style={{marginLeft: "10px",backgroundColor: "green",color: "white"}} onClick={() => dispatch({ type: 'todos/todoAdded', payload: todo })}>
            ADD ITEM
        </button>
      </div>
    );
  }

  export default CreateTodo;