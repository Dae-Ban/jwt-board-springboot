import MyPosts from "./MyPosts";
import NotFound from "./NotFound";
import Profile from "./Profile";
import WithdrawForm from "./WithdrawForm";

interface Props {
    selectedMenu: string;
}

const MypageMain = ({ selectedMenu }: Props) => {
    switch (selectedMenu) {
        case "profile":
            return <Profile />
        case "posts":
            return <MyPosts />
        case "withdraw":
            return <WithdrawForm />
        default:
            return <NotFound />;
    }
}

export default MypageMain;