import { React, createContext, useState, useEffect } from 'react';

const ToDoContext = createContext([]); 

function ToDoContextProvider(props) {

    const [list, setList] = useState(["clean the house","buy milk"]);
    const [todo, setToDo] = useState("");
    const {children} = props;

    useEffect(() =>{
    
    }, []);

    const createNewToDoItem = () => {
        const newList = [...list];
        newList.push(todo);
        setList(newList);
        setToDo("");
    };

    const deleteItem = indexToDelete => {
        let newList = [...list];
        if(indexToDelete>=0){
            newList.splice(indexToDelete,1);
        }
        setList(newList);
    };

    const handleKeyPress = e => {
        if (e.target.value !== "") {
          if (e.key === "Enter") {
            createNewToDoItem();
          }
        }
    };
      
    const handleInput = e => {
        setToDo(e.target.value);
    };

    return(
        <ToDoContext.Provider value={{list,todo,createNewToDoItem,deleteItem,handleKeyPress,handleInput}}>
            {children}
        </ToDoContext.Provider>
    )
}
export { ToDoContext };
export default ToDoContextProvider ;