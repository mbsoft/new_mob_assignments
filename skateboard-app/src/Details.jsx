import React, { useState, useEffect } from 'react';
import { publicDecrypt } from 'crypto';

const Details = ({ id }) => {

    const [board, setBoard] = useState({});

    useEffect(() => {
        fetch('http://localhost:8080/skate-board/' + id)
            .then(res => res.json())
            .then((data) => {
                setBoard(data)
            })
            .catch(console.log);
    }, [id]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const data = {
            "ownerName": event.target.ownerName.value,
            "description": event.target.description.value,
            "brand": event.target.brand.value,
            "weight": event.target.weight.value,
            "length": event.target.length.value,
            "location": event.target.location.value,
            "available": event.target.available.value
        }

        var options = {
            method: "PUT",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify(data)
        };

        fetch('http://localhost:8080/skate-board/' + id, options)
            .then(res => res.json())
            .then((data) => {
                setBoard(data)
            })
            .catch(console.log);
    }

    return <form id="board-form" onSubmit={handleSubmit}>
        <div className="form-row">
            <div className="form-group col-md-6">
                <label htmlFor="ownerName">Owner Name</label>
                <input type="text" className="form-control" id="ownerName" placeholder={board.ownerName} />
            </div>
            <div className="form-group col-md-6">
                <label htmlFor="description">Description</label>
                <input type="text" className="form-control" id="description" placeholder={board.description} />
            </div>
        </div>
        <div className="form-group">
            <label htmlFor="brand">Brand</label>
            <input type="text" className="form-control" id="brand" placeholder={board.brand} />
        </div>
        <div className="form-group">
            <label htmlFor="location">Location</label>
            <input type="text" className="form-control" id="location" placeholder={board.location} />
        </div>
        <div className="form-row">
            <div className="form-group col-md-6">
                <label htmlFor="length">Length</label>
                <input type="text" className="form-control" id="length" placeholder={board.length} />
            </div>
            <div className="form-group col-md-4">
                <label htmlFor="available">Available</label>
                <select id="available" className="form-control">
                    <option defaultValue="true">Choose...</option>
                    <option value="true">true</option>
                    <option value="false">false</option>
                </select>
            </div>
            <div className="form-group col-md-2">
                <label htmlFor="weight">Weight</label>
                <input type="text" className="form-control" id="weight" placeholder={board.weight} />
            </div>
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
    </form >
}
export default Details;