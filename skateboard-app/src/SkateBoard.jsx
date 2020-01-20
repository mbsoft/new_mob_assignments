import React from "react";
const SkateBoard = ({ id, name, brand, handleItemSelection }) => {
    const handleClick = () => {
        handleItemSelection(id)
    }
    return (
        <div className="card text-left">
            <div className="card-body">
                <h4 className="card-title">Owner Name: {name}</h4>
                <p className="card-text">Skate Board Brand: {brand}</p>
            </div>
            <button type="button" className="btn btn-primary" onClick={handleClick}>Select</button>
        </div>
    );
}

export default SkateBoard;