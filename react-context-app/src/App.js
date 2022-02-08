import logo from './logo.svg';
import './App.css';
import ToDoContextProvider,{ ToDoContext } from './Contexts/ToDoContext';
import Todo from './ToDo';

function App() {
  return (
    <ToDoContextProvider>
      <div className="App">
        <h1>To Do App</h1>
        <Todo></Todo>
      </div>
    </ToDoContextProvider>
  );
}

export default App;
