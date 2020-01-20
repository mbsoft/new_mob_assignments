import React, { useState, useEffect } from 'react';
import SkateBoard from './SkateBoard';

const SkateBoards = ({ handleItemSelection }) => {
    const [items, setItems] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/skate-board')
            .then(res => res.json())
            .then((data) => {
                setItems(data)
            })
            .catch(console.log);
    }, []);
    return (
        <>
            {items.map((item, index) => {
                return <SkateBoard key={item.id} id={item.id} name={item.ownerName} brand={item.brand} handleItemSelection={handleItemSelection}></SkateBoard>
            })}
        </>
    );
}
export default SkateBoards;