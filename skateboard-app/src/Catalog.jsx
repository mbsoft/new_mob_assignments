import React, { useState } from 'react';
import SkateBoards from './SkateBoards';
import Details from './Details';

const Catalog = () => {
    const [id, setId] = useState(1)

    const handleItemSelection = (id) => {
        setId(id)
    }

    return (
        <div className="row">
            <div className="col-lg-3 col-sm-6">
                <h4 className="card-title">Skate Boards</h4>
                <SkateBoards handleItemSelection={handleItemSelection}></SkateBoards>
            </div>
            <div className="col-lg-6 col-sm-12">
                <Details id={id}></Details>
            </div>
        </div>
    );

}

export default Catalog;