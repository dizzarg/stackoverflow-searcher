import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Container, Input} from "reactstrap";

export class SearchForm extends Component {

    constructor(props) {
        super(props);
    }

    handleChange = (event) => {
        this.setState({value: event.target.value});
    };

    handleSubmit = (event) => {
        event.preventDefault();
        console.log('Search query: ' + this.state.value);
        this.props.searchQuestions(this.state.value);
    };

    render() {
        return (
            <Container>
                <h1>Stack overflow searcher</h1>
                <form className="form-inline" onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <Input type="query" name="query" id="query"
                               placeholder="Have a question?" onChange={this.handleChange}/>
                        <Button type="submit" color="primary">Search</Button>
                    </div>
                </form>
            </Container>
        )
    }

}