import '../styles/about.css';

const AboutPage = () => {
    return (
        <div id="about">
            <h1>개발 환경</h1>
            <hr />
            <div id="about-content">
                <table>
                    <tbody>
                        <tr className='group-start'>
                            <th rowSpan={2}>프론트엔드</th>
                            <td>React</td>
                        </tr>
                        <tr>
                            <td>TypeScript</td>
                        </tr>
                        <tr className='group-start'>
                            <th rowSpan={3}>백엔드</th>
                            <td>Java 17</td>
                        </tr>
                        <tr>
                            <td>Spring Boot 3.5.4</td>
                        </tr>
                        <tr>
                            <td>JPA</td>
                        </tr>
                        <tr className='group-start'>
                            <th rowSpan={2}>데이터베이스</th>
                            <td>MySQL 8</td>
                        </tr>
                        <tr>
                            <td>Redis</td>
                        </tr>
                        <tr className='group-start'>
                            <th rowSpan={5}>개발 툴</th>
                            <td>Visual Studio Code</td>
                        </tr>
                        <tr>
                            <td>MySQL Workbench 8.0 CE</td>
                        </tr>
                        <tr>
                            <td>
                                Git
                            </td>
                        </tr>
                        <tr>
                            <td>Docker</td>
                        </tr>
                        <tr>
                            <td>Postman</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default AboutPage;