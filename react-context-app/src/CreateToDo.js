import React, { useContext } from "react";
import './App.css';
import { ToDoContext } from './Contexts/ToDoContext';

function CreateTodo({ addTodo }) {
    const allValues = useContext(ToDoContext);
  
    return (
      <div>
        <input
          type="text"
          className="input"
          value={allValues.todo}
          onChange={e => allValues.handleInput(e)}
          onKeyPress={allValues.handleKeyPress}
        />
        <button style={{marginLeft: "10px",backgroundColor: "green",color: "white"}} onClick={allValues.createNewToDoItem}>
            ADD ITEM
        </button>
      </div>
    );
  }

  export default CreateTodo;