import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';


export class Header extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <thead>
                <tr>
                    <th>#</th>
                    <th className="col-md-2">Question</th>
                    <th className="col-md-2">Created</th>
                    <th>Answered</th>
                    <th className="col-md-3">Tags</th>
                    <th>Score</th>
                    <th>Views</th>
                    <th className="col-md-3">Owner</th>
                </tr>
            </thead>
        )
    }

}