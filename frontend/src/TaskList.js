import React, { Component } from 'react';
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Table from "react-bootstrap/Table";
import {FaRedo, FaThumbsUp} from 'react-icons/fa';

class TaskList extends Component {

    constructor(props) {
        super(props);
        this.state = {tasks: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/tasks')
            .then(response => response.json())
            .then(data => this.setState({tasks: data._embedded.task}));
    }

    async remove(id) {
        await fetch(`/tasks/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedTasks = [...this.state.tasks].filter(i => i.id !== id);
            this.setState({tasks: updatedTasks});
        });
    }

    render() {
        const {tasks, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const taskList = tasks.map(task => {
            return <tr key={task.taskUUID}>
                <td style={{whiteSpace: 'nowrap'}}>{task.kind}</td>
                <td>{task.annotationTasks[0].kind}</td>
                <td>{task.annotationTasks[0].title}</td>
                <td>{task.annotationTasks[0].description}</td>
                <td>
                    <ButtonGroup >
                        <Link to={"/tasks/" + task.taskUUID}><Button size="sm" variant="primary">Edit</Button></Link>
                        <Button size="sm" variant="danger" onClick={() => this.remove(task.taskUUID)}>Delete</Button>
                        <Button size="sm" variant="success" onClick={()=> window.open("https://dev.visian.org/?origin=who&taskId=" + task.taskUUID, "_blank")}>Annotate</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container className={'pt-5'}>
                    <div className={'float-end'}>
                        <Button variant={'light'} onClick={() => this.componentDidMount()}><FaRedo /></Button>
                        <Button variant="success" tag={Link} to="/tasks/new">Add Task</Button>
                    </div>
                    <h3>Tasks</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">Kind</th>
                            <th width="20%">Annotation type</th>
                            <th width="30%">Title</th>
                            <th width="30%">Description</th>
                            <th width="30%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {taskList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default TaskList;