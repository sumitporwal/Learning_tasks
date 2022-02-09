import React, { useContext } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import logo from './logo.svg';
import './App.css';
import { AiTwotoneDelete} from 'react-icons/ai';
import CreateTodo from './CreateToDo';
import store from './store';

function Todo(props) {
  const dispatch = useDispatch();
  const todoList = useSelector(state => state.todoList);

  return (
    <div className="todo-div">
        <CreateTodo></CreateTodo>
        <div className="todo-list">
            {todoList.length > 0 && todoList.map((todo, index) => (
                <div className="todo" key={index}>
                    {todo}
                    <span>
                        <AiTwotoneDelete color='red' size={18}
                            onClick={() => dispatch({type: 'todos/todoDeleted',payload: todo})}
                        />
                    </span>
               </div>
            ))}
        </div>
    </div>
  );
};

export default Todo;