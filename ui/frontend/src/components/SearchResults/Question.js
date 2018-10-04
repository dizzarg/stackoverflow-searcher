import React, {Component} from 'react';
import {Badge} from "reactstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCheck, faUser} from '@fortawesome/free-solid-svg-icons'
import Moment from 'react-moment';

export class Question extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr key={this.props.id}>
                <td key="#">{this.props.id + 1}</td>
                <td key={this.props.question.title}>
                    <a target="_blank" href={this.props.question.link}
                       dangerouslySetInnerHTML={{__html: this.props.question.title}}/>
                </td>
                <td key={this.props.question.creationDate}>
                    <Moment format="YYYY/MM/DD">
                        {new Date(this.props.question.creationDate * 1000)}
                    </Moment>
                </td>
                <td key={this.props.question.answered}>
                    {this.props.question.answered && <FontAwesomeIcon icon={faCheck}/>}
                </td>
                <td>{this.props.question.tags.map((tag, i) => (
                    <Badge key={i} color="secondary">{tag}</Badge>
                ))}
                </td>
                <td key={this.props.question.score}>{this.props.question.score}</td>
                <td key={this.props.question.viewCount}>{this.props.question.viewCount}</td>
                <td key={this.props.question.owner}>
                    <a target="_blank" href={this.props.question.owner.link}>
                        <FontAwesomeIcon icon={faUser}/>&nbsp;
                        <span dangerouslySetInnerHTML={{__html: this.props.question.owner.name}}/>
                    </a>
                </td>
            </tr>
        )
    }

}