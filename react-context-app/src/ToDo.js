import React, { useContext } from 'react';
import logo from './logo.svg';
import './App.css';
import { ToDoContext } from './Contexts/ToDoContext';
import { AiTwotoneDelete} from 'react-icons/ai';
import CreateTodo from './CreateToDo';

function Todo(props) {
  const allValues = useContext(ToDoContext);
  const todoList = allValues.list;
  return (
    <div className="todo-div">
        <CreateTodo></CreateTodo>
        <div className="todo-list">
            {todoList.map((todo, index) => (
                <div className="todo" key={index}>
                    {todo}
                    <span>
                        <AiTwotoneDelete color='red' size={18}
                            onClick={() => allValues.deleteItem(index)}
                        />
                    </span>
               </div>
            ))}
        </div>
    </div>
  );
};

export default Todo;