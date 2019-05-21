package base

class NavigationManager() : IRouter {

    override fun submit(flow: Flow?) {
        flow?.start("context")
    }

}
