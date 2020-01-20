import React, { useState } from "react";
const SkateBoard = ({ id, name, brand, handleItemSelection }) => {
    const handleClick = () => {
        handleItemSelection(id)
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('http://localhost:8080/skate-board/' + id,
            {
                method: "DELETE"
            })
            .catch(console.log);
        window.location.reload(false);
    }
    return (
        <div className="card text-left">
            <div className="card-body">
                <h4 className="card-title">Owner Name: {name}</h4>
                <p className="card-text">Skate Board Brand: {brand}</p>
            </div>
            <div className="row">
                <div className="col-lg-3 col-sm-6">
                    <button type="button" className="btn btn-primary" onClick={handleClick}>Select</button>
                </div>
                <div className="col-lg-3 col-sm-6">
                    <button type="button" className="btn btn-primary" onClick={handleSubmit}>Delete</button>
                </div>
            </div>


        </div>
    );
}

export default SkateBoard;