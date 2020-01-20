import React, { useState } from 'react';
import SkateBoards from './SkateBoards';
import Details from './Details';
import AddBoard from './AddBoard';

const Catalog = () => {
    const [id, setId] = useState()

    const handleItemSelection = (id) => {
        setId(id)
    }

    return (
        <div className="row">
            <div className="col-lg-4 col-sm-8">
                <h4 className="card-title">Skate Boards</h4>
                <AddBoard></AddBoard>
                <SkateBoards handleItemSelection={handleItemSelection}></SkateBoards>
            </div>
            {id > 0 &&
                <div className="col-lg-6 col-sm-12">
                    <Details id={id}></Details>
                </div>
            }
        </div>
    );

}

export default Catalog;