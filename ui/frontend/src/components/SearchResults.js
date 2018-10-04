import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Table } from 'reactstrap';
import {Header} from "./SearchResults/Header";
import {Body} from "./SearchResults/Body";


export class SearchResults extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Table>
                <Header/>
                <Body questions={this.props.questions}/>
            </Table>
        )
    }

}