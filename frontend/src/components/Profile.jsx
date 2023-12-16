import React from 'react';
import {useSelector} from "react-redux";

const Profile = () => {
    const {userInfo} = useSelector((state) => state.user)
    return (
        <div>
            <figure>{userInfo?.username.charAt(0).toUpperCase()}</figure>
            <span>
        Welcome <strong>{userInfo?.username}!</strong> You can view this page
        because you're logged in
      </span>
        </div>
    );
};

export default Profile;